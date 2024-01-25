This program lets you print out information about all certificates that a FusionAuth instance has.

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

## Thanks

Thanks to Rod Martins for contributing this.
