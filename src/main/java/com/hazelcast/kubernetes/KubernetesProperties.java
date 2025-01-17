/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.kubernetes;

import com.hazelcast.config.properties.PropertyDefinition;
import com.hazelcast.config.properties.SimplePropertyDefinition;
import com.hazelcast.core.TypeConverter;

import static com.hazelcast.config.properties.PropertyTypeConverter.BOOLEAN;
import static com.hazelcast.config.properties.PropertyTypeConverter.INTEGER;
import static com.hazelcast.config.properties.PropertyTypeConverter.STRING;

/**
 * <p>Configuration class of the Hazelcast Discovery Plugin for <a href="http://kubernetes.io">Kubernetes</a>.</p>
 * <p>For possible configuration properties please refer to the public constants of this class.</p>
 */
public final class KubernetesProperties {

    /**
     * <p>Configuration System Environment Prefix: <tt>hazelcast.kubernetes.</tt></p>
     * Defines the prefix for system environment variables and JVM command line parameters.<br>
     * Defining or overriding properties as JVM parameters or using the system environment, those
     * properties need to be prefixed to prevent collision on property names.<br>
     * Example: {@link #SERVICE_DNS} will be:
     * <pre>
     *     -Dhazelcast.kubernetes.service-dns=value
     * </pre>
     * For kubernetes and openshift there is a special rule where the environment variables are
     * provided in C-identifier style, therefore the prefix is converted to uppercase and dots
     * and dashed will be replaced with underscores:
     * <pre>
     *     HAZELCAST_KUBERNETES_SERVICE_DNS=value
     * </pre>
     */
    public static final String KUBERNETES_SYSTEM_PREFIX = "hazelcast.kubernetes.";

    /**
     * <p>Configuration key: <tt>service-dns</tt></p>
     * Defines the DNS service lookup domain. This is defined as something similar
     * to <tt>my-svc.my-namespace.svc.cluster.local</tt>.<br>
     * For more information please refer to the official documentation of the Kubernetes DNS addon,
     * <a href="https://github.com/kubernetes/kubernetes/tree/v1.0.6/cluster/addons/dns">here</a>.
     */
    public static final PropertyDefinition SERVICE_DNS = property("service-dns", STRING);

    /**
     * <p>Configuration key: <tt>service-dns-timeout</tt></p>
     * Defines the DNS service lookup timeout in seconds. Defaults to: 5 secs.
     */
    public static final PropertyDefinition SERVICE_DNS_TIMEOUT = property("service-dns-timeout", INTEGER);

    /**
     * <p>Configuration key: <tt>service-name</tt></p>
     * Defines the service name of the POD to lookup through the Service Discovery REST API of Kubernetes.
     */
    public static final PropertyDefinition SERVICE_NAME = property("service-name", STRING);
    /**
     * <p>Configuration key: <tt>service-label-name</tt></p>
     * Defines the service label to lookup through the Service Discovery REST API of Kubernetes.
     */
    public static final PropertyDefinition SERVICE_LABEL_NAME = property("service-label-name", STRING);
    /**
     * <p>Configuration key: <tt>service-label-value</tt></p>
     * Defines the service label value to lookup through the Service Discovery REST API of Kubernetes.
     */
    public static final PropertyDefinition SERVICE_LABEL_VALUE = property("service-label-value", STRING);

    /**
     * <p>Configuration key: <tt>namespace</tt></p>
     * Defines the namespace of the application POD through the Service Discovery REST API of Kubernetes.
     */
    public static final PropertyDefinition NAMESPACE = property("namespace", STRING);

    /**
     * <p>Configuration key: <tt>resolve-not-ready-addresses</tt></p>
     * Defines if not ready addresses should be evaluated to be discovered on startup.
     */
    public static final PropertyDefinition RESOLVE_NOT_READY_ADDRESSES = property("resolve-not-ready-addresses", BOOLEAN);

    /**
     * <p>Configuration key: <tt>use-node-name-as-external-address</tt></p>
     * Defines if the node name should be used as external address, instead of looking up the external IP using
     * the <code>/nodes</code> resource. Default is false.
     */
    public static final PropertyDefinition USE_NODE_NAME_AS_EXTERNAL_ADDRESS = property("use-node-name-as-external-address",
            BOOLEAN);

    /**
     * <p>Configuration key: <tt>kubernetes-api-retries</tt></p>
     * Defines the number of retries to Kubernetes API. Defaults to: 3.
     */
    public static final PropertyDefinition KUBERNETES_API_RETIRES = property("kubernetes-api-retries", INTEGER);

    /**
     * <p>Configuration key: <tt>kubernetes-master</tt></p>
     * Defines an alternative address for the kubernetes master. Defaults to: <tt>https://kubernetes.default.svc</tt>
     */
    public static final PropertyDefinition KUBERNETES_MASTER_URL = property("kubernetes-master", STRING);

    /**
     * <p>Configuration key: <tt>api-token</tt></p>
     * Defines an oauth token for the kubernetes client to access the kubernetes REST API. Defaults to reading the
     * token from the auto-injected file at: <tt>/var/run/secrets/kubernetes.io/serviceaccount/token</tt>
     */
    public static final PropertyDefinition KUBERNETES_API_TOKEN = property("api-token", STRING);

    /**
     * Configuration key: <tt>ca-certificate</tt>
     * CA Authority certificate from Kubernetes Master, defaults to reading the certificate from the auto-injected file at:
     * <tt>/var/run/secrets/kubernetes.io/serviceaccount/ca.crt</tt>
     */
    public static final PropertyDefinition KUBERNETES_CA_CERTIFICATE = property("ca-certificate", STRING);

    /**
     * <p>Configuration key: <tt>service-port</tt></p>
     * If specified with a value greater than 0, its value defines the endpoint port of the service (overriding the default).
     */
    public static final PropertyDefinition SERVICE_PORT = property("service-port", INTEGER);

    // Prevent instantiation
    private KubernetesProperties() {
    }

    private static PropertyDefinition property(String key, TypeConverter typeConverter) {
        return new SimplePropertyDefinition(key, true, typeConverter);
    }
}
