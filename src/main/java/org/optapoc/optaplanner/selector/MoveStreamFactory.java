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
import java.util.Random;
import java.util.function.Function;

import org.optapoc.domain.Employee;
import org.optapoc.domain.Shift;
import org.optapoc.optaplanner.move.Pillar;

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

}
