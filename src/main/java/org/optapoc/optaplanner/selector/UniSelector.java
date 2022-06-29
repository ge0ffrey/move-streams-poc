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

package org.optapoc.optaplanner.selector;

import java.util.List;
import java.util.function.Function;

import org.optapoc.optaplanner.move.Pillar;

public class UniSelector<A> {

    private final MoveStreamFactory moveStreamFactory;
    private final List<A> list;

    public UniSelector(MoveStreamFactory moveStreamFactory, List<A> list) {
        this.moveStreamFactory = moveStreamFactory;
        this.list = list;
    }

    // ************************************************************************
    // Public API
    // ************************************************************************

    public <B> BiSelector<A, B> combine(Class<B> bClass) {
        UniSelector<B> other = moveStreamFactory.select(bClass);
        return new BiSelector<>(moveStreamFactory, this, other);
    }

    public <ValueB_, EntityB_> BiSelector<A, Pillar<ValueB_, EntityB_>>
            combinePillar(Class<EntityB_> entityBClass, Function<EntityB_, ValueB_> entityToValueBFunction) {
        UniSelector<Pillar<ValueB_, EntityB_>> other = moveStreamFactory.selectPillar(entityBClass, entityToValueBFunction);
        return new BiSelector<>(moveStreamFactory, this, other);
    }

    // ************************************************************************
    // Internal API
    // ************************************************************************
    // TODO Hide internal API

    public A pick() {
        return list.get(moveStreamFactory.getRandom().nextInt(list.size()));
    }

}
