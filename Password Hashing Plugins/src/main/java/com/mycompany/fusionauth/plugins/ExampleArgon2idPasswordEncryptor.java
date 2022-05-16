/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.mycompany.fusionauth.plugins;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import de.mkammerer.argon2.Argon2Advanced;
import de.mkammerer.argon2.Argon2Factory;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;


/**
 * This is an example of an Argon2id hashing algorithm.
 *
 * <p>
 * This code is provided to assist in your deployment and management of FusionAuth. Use of this
 * software is not covered under the FusionAuth license agreement and is provided "as is" without
 * warranty. https://fusionauth.io/license
 * </p>
 *
 * @author Matthew Hartstonge
 * @see <a href="https://tools.ietf.org/html/draft-irtf-cfrg-argon2-12"> Argon2 IETF RFC </a>
 * @see <a href="https://github.com/phxql/argon2-jvm"> Argon2-jvm Github Repo </a>
 * @see <a href="https://github.com/P-H-C/phc-winner-argon2/blob/master/argon2-specs.pdf"> Original Argon2 Whitepaper </a>
 */
public class ExampleArgon2idPasswordEncryptor implements PasswordEncryptor {
    private final Argon2Advanced argon2;

    /**
     * timeCost (t), specifies a number of passes (iterations) used to tune the
     * running time independently of the memory size.
     */
    private int timeCost;

    /**
     * memoryCost (m), specifies the hashing memory size in kibibytes.
     */
    private int memoryCost;

    /**
     * parallelism (p), specifies the degree of parallelism, which is how many
     * independent (but synchronizing) computational chains (lanes/thread) can
     * be run.
     */
    private int parallelism;

    public ExampleArgon2idPasswordEncryptor() {
        this.setMemoryCost(64);
        this.setParallelism(1);
        this.setTimeCost(this.defaultFactor());
        this.argon2 = Argon2Factory.createAdvanced(Argon2Factory.Argon2Types.ARGON2id);
    }

    @Override
    public int defaultFactor() {
        // The Argon2id variant with t=1 and maximum available memory is
        // RECOMMENDED as a default setting for all environments.
        //
        // Refer: https://tools.ietf.org/html/draft-irtf-cfrg-argon2-12#section-7.4
        return 1;
    }

    @Override
    public String encrypt(String password, String salt, int factor) {
        this.setTimeCost(factor);

        String hash = this.argon2.hash(
                this.timeCost,
                this.memoryCost,
                this.parallelism,
                password.toCharArray(),
                StandardCharsets.UTF_8,
                Base64.getDecoder().decode(salt)
        );

        return new String(Base64.getEncoder().encode(hash.getBytes()));
    }

    /**
     * setTimeCost configures the cost of iterations.
     *
     * @param timeCost - The number of passes (time cost/iterations) to use when hashing.
     * @throws IllegalArgumentException - If an invalid time cost is configured.
     */
    public void setTimeCost(int timeCost) {
        if (timeCost < 1) {
            throw new IllegalArgumentException("Invalid Time Cost value [" + timeCost + "] it must be an integer greater, or equal to 1");
        }

        this.timeCost = timeCost;
    }

    /**
     * setMemoryCost sets the memory cost in megabytes.
     *
     * @param memory - The amount of memory in megabytes to use when hashing.
     * @throws IllegalArgumentException - If an invalid memory cost is provided.
     */
    public void setMemoryCost(int memory) {
        int kibibytes = memory * 1024;
        if (kibibytes < 1 || kibibytes > 16_777_215) {
            throw new IllegalArgumentException("Invalid Memory Cost value [" + memory + "] it must be an integer from 1 to 16,383 ((2^(24)-1)/1024).");
        }

        this.memoryCost = kibibytes;
    }

    /**
     * setParallelism sets the degree of parallelism to use when hashing.
     *
     * @param parallelism - The degree of parallelism to use when hashing.
     * @throws IllegalArgumentException - If an invalid degree of parallelism is provided.
     */
    public void setParallelism(int parallelism) {
        if (parallelism < 1) {
            throw new IllegalArgumentException("Invalid Parallelism value [" + parallelism + "] it must be an integer greater, or equal to 1");
        }

        this.parallelism = parallelism;
    }
}
