/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optapoc.optaplanner.move;

import java.util.List;

public class Pillar<Value_, Entity_> {

    private Value_ value;
    private List<Entity_> entityList;

    public Pillar(Value_ value, List<Entity_> entityList) {
        this.value = value;
        this.entityList = entityList;
    }

    public Value_ getValue() {
        return value;
    }

    public List<Entity_> getEntityList() {
        return entityList;
    }

    @Override
    public String toString() {
        return value + "(" + entityList + ")";
    }

}
