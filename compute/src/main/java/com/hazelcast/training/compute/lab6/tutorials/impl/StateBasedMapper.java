/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.training.compute.lab6.tutorials.impl;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import com.hazelcast.training.compute.lab6.model.Person;

public class StateBasedMapper
        implements Mapper<String, Person, String, Person> {

    private String state;

    public StateBasedMapper() {
    }

    public StateBasedMapper(String state) {
        this.state = state;
    }

    @Override
    public void map(String key, Person value, Context<String, Person> context) {
        if (state != null) {
            if (!state.equals(value.getState())) {
                return;
            }
        }

        context.emit(value.getState(), value);
    }
}
