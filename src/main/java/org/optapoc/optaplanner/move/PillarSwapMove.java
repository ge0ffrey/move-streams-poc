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

public class PillarSwapMove<Value_, Entity_> implements Move {

    private Pillar<Value_, Entity_> a;
    private Pillar<Value_, Entity_> b;

    public PillarSwapMove(Pillar<Value_, Entity_> a, Pillar<Value_, Entity_> b) {
        this.a = a;
        this.b = b;
    }

    public Pillar<Value_, Entity_> getA() {
        return a;
    }

    public Pillar<Value_, Entity_> getB() {
        return b;
    }

    @Override
    public String toString() {
        return a + " <-> " + b;
    }

}
