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

import java.util.Iterator;
import java.util.function.Function;

import org.optapoc.optaplanner.move.Move;

public class BiSelector<A, B> {

    private final MoveStreamFactory moveStreamFactory;
    private final UniSelector<A> selectorA;
    private final UniSelector<B> selectorB;

    public BiSelector(MoveStreamFactory moveStreamFactory, UniSelector<A> selectorA, UniSelector<B> selectorB) {
        this.moveStreamFactory = moveStreamFactory;
        this.selectorA = selectorA;
        this.selectorB = selectorB;
    }

    // ************************************************************************
    // Public API
    // ************************************************************************

    public <Move_ extends Move> Iterator<Move_> move(Function<BiSelector<A, B>, Iterator<Move_>> moveFunction) {
        return moveFunction.apply(this);
    }

    // ************************************************************************
    // Internal API
    // ************************************************************************
    // TODO Hide internal API

    public UniSelector<A> getSelectorA() {
        return selectorA;
    }

    public UniSelector<B> getSelectorB() {
        return selectorB;
    }

}
