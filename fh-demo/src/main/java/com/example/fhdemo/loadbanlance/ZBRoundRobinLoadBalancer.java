package com.example.fhdemo.loadbanlance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ZBRoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static final Log log = LogFactory.getLog(ZBRoundRobinLoadBalancer.class);
    final AtomicInteger position;
    final String serviceId;
    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public ZBRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this(serviceInstanceListSupplierProvider, serviceId, (new Random()).nextInt(1000));
    }

    public ZBRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId, int seedPosition) {
        //super(serviceInstanceListSupplierProvider,serviceId,seedPosition);
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
        this.position = new AtomicInteger(seedPosition);
    }

    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = (ServiceInstanceListSupplier)this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map((serviceInstances) -> {
            return this.processInstanceResponse(supplier, serviceInstances,request);
        });
    }

    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, List<ServiceInstance> serviceInstances, Request request) {
        Response<ServiceInstance> serviceInstanceResponse = this.getInstanceResponse(serviceInstances,request);
        if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
            ((SelectedInstanceCallback)supplier).selectedServiceInstance((ServiceInstance)serviceInstanceResponse.getServer());
        }

        return serviceInstanceResponse;
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
        if (instances.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("No servers available for service: " + this.serviceId);
            }

            return new EmptyResponse();
        } else {
            int pos = this.position.incrementAndGet() & 2147483647;
            DefaultRequestContext requestContext = (DefaultRequestContext) request.getContext();
            RequestData clientRequest = (RequestData) requestContext.getClientRequest();
            String sessionId = (String) clientRequest.getAttributes().get("sessionId");
            ServiceInstance                 instance = (ServiceInstance) instances.get(pos % instances.size());

/*            if(instanceSession.get(sessionId) != null) {
                instance = instances.stream().filter(f -> f.getInstanceId().equals(instanceSession.get(sessionId))).findFirst().get();
            }else {
                instanceSession.put(sessionId, instance.getInstanceId());
            }*/
            return new DefaultResponse(instance);
        }
    }
}
