# Fusionauth Contributions
Mono-repo of community contributed FusionAuth modules and example code.

## Client Libraries
Links to code library projects not officially supported by FusionAuth.  See [README][client-libraries] for more details.

## Kubernetes
A collection of yaml manifests to deploy FusionAuth resources to a Kubernetes cluster.  See the [Kubernetes README][kubernetes] for detailed inventory and usage.

## OpenShift
A collection of yaml manifests to deploy FusionAuth resources to an OpenShift cluster.  See the [OpenShift README][openshift] for detailed inventory and usage.

## proxy-docker
A collection of web proxy configurations and docker compose files to deploy a proxy in front of FusionAuth.  See the respective README files linked [here][proxy-docker] for usage.

## Reverse Proxy Configurations
A collection of reverse proxy configuration files for [HA Proxy][ha-proxy], [Traefik][traefik], [Apache][apache], [CloudFront][cloudfront] and [Nginx][nginx].

## Contributions

Please feel free to submit a PR against this repository with any community contributions you run across.

## Community Guidelines

As part of the FusionAuth community, please abide by [the Code of Conduct](https://fusionauth.io/community/forum/topic/1000/code-of-conduct).

## Credits

* [@The-Funk](https://github.com/The-Funk) for adding the [HAProxy](http://www.haproxy.org/) config.
* [@soullivaneuh](https://github.com/soullivaneuh) for submitting the [Traefik](https://containo.us/traefik/) config.
* [@cpellens](https://github.com/cpellens) for submitting the [Nginx dev config](https://www.nginx.com/) config.
* [@pre-emptive](https://github.com/pre-emptive) for submitting the [Apache Virtual host config](https://httpd.apache.org/) config.
* [@brennan-karrer](https://github.com/brennan-karrer) for the Elixir client libraries.
* [@f0rever-johnson](https://github.com/f0rever-johnson) for the swift client libraries.
* [@sims-security](https://github.com/sims-security) Thank you for the proxy-docker section.
* [@ssirag](https://github.com/ssirag) Thank you for the feedback and bug reports.
* [@minyangu](https://github.com/minyangu) Thank you for [PR #3](https://github.com/FusionAuth/fusionauth-containers/pull/3) to enhance our docker-compose example.
* [János Veres](https://github.com/nadilas) Thank you for building out an example Kubernetes configuration via [PR #6](https://github.com/FusionAuth/fusionauth-containers/pull/6), this will be very valuable to the FusionAuth community.
* [@trollr](https://github.com/trollr) and the [Ninjaneers team](https://www.ninjaneers.de/) for the helm configuration and all of their contribution.
* [@vladyslav2](https://github.com/vladyslav2) for [PR #15](https://github.com/FusionAuth/fusionauth-containers/pull/15) to make the Docker Compose example work better when invoked via `bash`.
* [@drpebcak](https://github.com/drpebcak) for all of your contribution and assistance in this repo!
* [@sims-security](https://github.com/sims-security) for the proxy examples in `proxy-docker/`!
* The FusionAuth team - couldn't have done it without you!

### License
Copyright 2021 FusionAuth, LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[client-libraries]: ./client-libraries/READEME.md
[kubernetes]:       ./kuberentes/
[openshift]:        ./openshift/
[proxy-docker]:     ./proxy-docker/
[ha-proxy]:         ./reverse-proxy-configurations/ha-proxy
[traefik]:          ./reverse-proxy-configurations/traefik
[apache]:           ./reverse-proxy-configurations/apache
[cloudfront]:       ./reverse-proxy-configurations/cloudfront
[nginx]:            ./reverse-proxy-configurations/nginx
[ha-proxy]:         ./reverse-proxy-configurations/ha-proxy
[license]:          https://fusionauth.io/license
