/*
 * Copyright 2017 original authors
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
package io.micronaut.http.server.netty.java;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Parameter;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.HttpParameters;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Parameter;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Graeme Rocher
 * @since 1.0
 */
@Controller("/java/parameter")
public class ParameterController {
    @Get("/")
    String index(Integer max) {
        return "Parameter Value: " + max;
    }

    @Get
    String simple(@Parameter Integer max) {
        return "Parameter Value: " + max;
    }

    @Get
    String named(@Parameter("maximum") Integer max) {
        return "Parameter Value: " + max;
    }

    @Get
    String optional(@Parameter Optional<Integer> max) {
        return "Parameter Value: " + max.orElse(10);
    }

    @Get
    String nullable(@Nullable Integer max) {
        return "Parameter Value: " + (max != null ? max : "null");
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    String nullableBody(@Nullable Integer max) {
        return "Body Value: " + (max != null ? max : "null");
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    String requiresBody(Integer max) {
        return "Body Value: " + (max != null ? max : "null");
    }

    @Get
    String all(HttpParameters parameters) {
        return "Parameter Value: " + parameters.get("max", Integer.class, 10);
    }

    @Get
    String map(Map<String, Integer> values) {
        return "Parameter Value: " + values.get("max") + values.get("offset");
    }

    @Get
    String list(List<Integer> values) {
        assert values.stream().allMatch(val -> val instanceof Integer);
        return "Parameter Value: " + values;
    }

    @Get
    String optionalList(Optional<List<Integer>> values) {
        if (values.isPresent()) {
            assert values.get().stream().allMatch(val -> val instanceof Integer);
            return "Parameter Value: " + values.get();
        } else {
            return "Parameter Value: none";
        }
    }
}