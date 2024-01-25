This program lets you print out information about all keys that a FusionAuth instance has. This can be useful if you are trying to delete one and are unable to see which application is using them to sign a JWT, for example.

## Prerequisites

You need to have dotnet installed.

## Usage

Edit the file and update the URL to point to your FusionAuth instance. Also update the API key to be a valid API key.

Minimum privileges:

* `GET` on `/api/application`
* `GET` on `/api/identity-provider`
* `GET` on `/api/key`
* `GET` on `/api/tenant`

Then run it with `dotnet run`

## Other Notes

You could build this with any of the other [FusionAuth client libraries](https://fusionauth.io/docs/sdks).

Learn more about FusionAuth: https://fusionauth.io/

See also https://github.com/FusionAuth/fusionauth-issues/issues/2486 for a related issue.

## Thanks

Thanks to Rod Martens for contributing this.


