## Contributed Password Hashers

If you need to write a password hasher (also known as a password encryptor) to import users into FusionAuth without requiring them to change their password, you may use the contributed hashers in this project. This is intended to be used with the Writing a Plugin guide provided in the FusionAuth documentation. 

The Writing a Plugin guide is found here:  https://fusionauth.io/docs/v1/tech/plugins/writing-a-plugin

### Example code

Any example password encryptors provided in this repository is provided to assist in your deployment and management of FusionAuth. Use of this software is not covered under the FusionAuth license agreement and is provided "as is" without warranty. https://fusionauth.io/license

This repository contains contributed and example hashers that should work with FusionAuth.

If you want a simple example to follow along in implementing your own hashing algorithm, please visit the [example plugin repository](https://github.com/FusionAuth/fusionauth-example-password-encryptor/).

### Building

### Building with Maven


```bash
$ mvn install
```

### Building with Savant

**Note:** This project uses the Savant build tool. To compile using Savant, follow these instructions:

```bash
$ mkdir ~/savant
$ cd ~/savant
$ wget http://savant.inversoft.org/org/savantbuild/savant-core/1.0.0/savant-1.0.0.tar.gz
$ tar xvfz savant-1.0.0.tar.gz
$ ln -s ./savant-1.0.0 current
$ export PATH=$PATH:~/savant/current/bin/
```
