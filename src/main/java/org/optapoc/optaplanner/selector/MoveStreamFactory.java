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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.optapoc.domain.Employee;
import org.optapoc.domain.Shift;
import org.optapoc.optaplanner.move.Pillar;
import org.optapoc.optaplanner.move.PillarSwapMove;
import org.optapoc.optaplanner.move.SwapMove;

public class MoveStreamFactory {

    private Random random = new Random(37);

    private List<Employee> employeeList;
    private List<Shift> shiftList;

    public MoveStreamFactory(List<Employee> employeeList, List<Shift> shiftList) {
        this.employeeList = employeeList;
        this.shiftList = shiftList;
    }

    protected Random getRandom() {
        return random;
    }

    protected List<Employee> getEmployeeList() {
        return employeeList;
    }

    protected List<Shift> getShiftList() {
        return shiftList;
    }

    public <A> UniSelector<A> select(Class<A> aClass) {
        return new UniSelector<>(this, aClass);
    }

    public <ValueA_, EntityA_> UniSelector<Pillar<ValueA_, EntityA_>> selectPillar(Class<EntityA_> entityAClass,
            Function<EntityA_, ValueA_> entityToValueAFunction) {
        return new UniSelector<>(this, null); // TODO FIXME
    }

    public <A> Iterator<SwapMove<A>> swap(UniSelector<A> selector1, UniSelector<A> selector2) {
        return new Iterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count++ < 10; // TODO HACK
            }

            @Override
            public SwapMove<A> next() {
                A a1 = selector1.pick();
                A a2 = selector2.pick();
                return new SwapMove<>(a1, a2);
            }
        };
    }

    public <Value_, Entity_> Iterator<PillarSwapMove<Value_, Entity_>> swapPillar(
            UniSelector<Pillar<Value_, Entity_>> selector1, UniSelector<Pillar<Value_, Entity_>> selector2) {
        Map<Employee, List<Shift>> employeeToShiftMap = shiftList.stream().collect(Collectors.groupingBy(Shift::getEmployee));
        return new Iterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count++ < 10; // TODO HACK
            }

            @Override
            public PillarSwapMove<Value_, Entity_> next() {
                Pillar<Value_, Entity_> pillar1 = selector1.pick();
                Pillar<Value_, Entity_> pillar2 = selector2.pick();
                return new PillarSwapMove<>(pillar1, pillar2);
            }
        };
    }

}
