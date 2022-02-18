# Cloudfront

This is an AWS CDN that you can use to front FusionAuth.

## Setup

There's a sample JSON config in this directory. If you use it, make sure to update `REPLACE` with your values. If you find issues with it, please submit a PR.

In general:

* ensure the CDN isn't caching anything (you can re-enable caching and test once you have the initial error taken care of)
* ensure the CDN is passing through all query parameters, cookies and headers
* set up the load balancer in front of FusionAuth to respond to the hostname of the CDN. 
* set the CDN to forward headers appropriately. In particular, ensure that X-Forwarded-Host is the value of the hostname of the CDN (XXX.cloudfront.net for example) and that X-Forwarded-Proto is https.

## Legacy path support

From a customer's experience, this below example uses Cloudfront to redirect users from a legacy path (`/authorize`) to the standard FusionAuth path: `/oauth2/authorize`. This allows you to use FusionAuth without requiring any changes to customer configuration (if they use the legacy path, they'll be transparently migrated.)

We created a cloudfront instance using our CNAME fusionauth-dev.id.example.com as the alternate domain name. We are also using our own ssl cert in this case.

We have created an origin for example-dev.fusionauth.io, really nothing special.

We have an associated behaviour with this origin allowing any http methods and no caching. We are able to attach this behaviour to a lambda@edge function:

```
'use strict';
const querystring = require('querystring');
exports.handler = (event, context, callback) => {
    const request = event.Records[0].cf.request;
    if (request.uri.startsWith('/authorize')) {
        const params = querystring.parse(request.querystring);
        const redirectResponse = {
            status: '301',
            statusDescription: 'Moved Permanently',
            headers: {
              'location': [{
                key: 'Location',
                value: '/oauth2' + request.uri + '?' + querystring.stringify(params),
              }],
              'cache-control': [{
                key: 'Cache-Control',
                value: "max-age=3600"
              }],
            },
        };
        callback(null, redirectResponse);
    }
    callback(null, request);
};
```
