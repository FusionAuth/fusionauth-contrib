# fusionauth-contrib

Community contributed examples and code.

## How to build the password hashing project

- Install Docker.
- Fork this project on GitHub and clone it locally.
- Open a terminal in the directory and run the code below.

```bash
docker run -it --name javabox -v .:/workspace richardjecooke/javaimage
# inside the docker container:
cd "Password Hashing Plugins";
mvn dependency:resolve;
mvn clean install;
/opt/savant-1.0.0/bin/sb build;
exit
```

## Credits

* [@The-Funk](https://github.com/The-Funk) for adding the [HAProxy](http://www.haproxy.org/) config.
* [@soullivaneuh](https://github.com/soullivaneuh) for submitting the [Traefik](https://containo.us/traefik/) config.
* [@cpellens](https://github.com/cpellens) for submitting the [Nginx dev config](https://www.nginx.com/) config.
* [@pre-emptive](https://github.com/pre-emptive) for submitting the [Apache Virtual host config](https://httpd.apache.org/) config.
* [@brennan-karrer](https://github.com/brennan-karrer) for the Elixir client libraries.
* [@f0rever-johnson](https://github.com/f0rever-johnson) for the swift client libraries.
* János Veres for the Istio kubernetes config.

## Contributions

Please feel free to submit a PR against this repository with any community contributions you run across.

## Community Guidelines

As part of the FusionAuth community, please abide by [the Code of Conduct](https://fusionauth.io/community/forum/topic/1000/code-of-conduct).

## Notice

This is community maintained and is provided to assist in your deployment and management of FusionAuth. Use of this software is not covered under the FusionAuth license agreement and is provided "as is" without warranty. https://fusionauth.io/license