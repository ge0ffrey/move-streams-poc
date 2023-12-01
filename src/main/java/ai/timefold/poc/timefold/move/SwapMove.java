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

package ai.timefold.poc.timefold.move;

public class SwapMove<A> implements Move {

    private A a1;
    private A a2;

    public SwapMove(A a1, A a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public A getA1() {
        return a1;
    }

    public A getA2() {
        return a2;
    }

    @Override
    public String toString() {
        return a1 + " <-> " + a2;
    }
}
