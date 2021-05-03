From a customer's experience, this example uses Cloudfront to redirect users from a legacy path (`/authorize`) to the standard FusionAuth path: `/oauth2/authorize`. This allows you to use FusionAuth without requiring any changes to customer configuration (if they use the legacy path, they'll be transparently migrated.)

We created a cloudfront instance using our CNAME fusionauth-dev.id.example.com as the alternate domain name. We are also using our own ssl cert in this case.

We have created an origin for example-dev.fusionauth.io like so, nothing really special.

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
