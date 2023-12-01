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

package ai.timefold.poc.timefold.selector;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.timefold.poc.domain.Employee;
import ai.timefold.poc.domain.Shift;
import ai.timefold.poc.timefold.move.Pillar;

public class MoveStreamFactory {

    private Random random = new Random(37);

    private List<Employee> employeeList;
    private List<Shift> shiftList;

    // Cached supplies
    private Map<Employee, List<Shift>> employeeToShiftMap = null;

    public MoveStreamFactory(List<Employee> employeeList, List<Shift> shiftList) {
        this.employeeList = employeeList;
        this.shiftList = shiftList;
    }

    // ************************************************************************
    // Public API
    // ************************************************************************

    public <A> UniSelector<A> select(Class<A> aClass) {
        List<A> list;
        if (aClass.isAssignableFrom(Shift.class)) {
            list = (List<A>) shiftList;
        } else if (aClass.isAssignableFrom(Employee.class)) {
            list = (List<A>) employeeList;
        } else {
            throw new UnsupportedOperationException("Unknown aClass " + aClass);
        }
        return new UniSelector<>(this, list);
    }

    public <ValueA_, EntityA_> UniSelector<Pillar<ValueA_, EntityA_>> selectPillar(Class<EntityA_> entityAClass,
            Function<EntityA_, ValueA_> entityToValueAFunction) {
        Map<ValueA_, List<EntityA_>> map;
        if (entityAClass.isAssignableFrom(Shift.class)) { // TODO check if entityToValueAFunction is getEmployee
            map = (Map) demandEmployeeToShiftMap();
        } else {
            throw new UnsupportedOperationException("Unknown entityAClass " + entityAClass);
        }
        // TODO this breaks incremental updates during steps
        return new UniSelector<>(this, map.entrySet().stream()
                .map(entry -> new Pillar<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }

    // ************************************************************************
    // Internal API
    // ************************************************************************
    // TODO Hide internal API

    protected Random getRandom() {
        return random;
    }

    protected List<Employee> getEmployeeList() {
        return employeeList;
    }

    protected List<Shift> getShiftList() {
        return shiftList;
    }

    public Map<Employee, List<Shift>> demandEmployeeToShiftMap() {
        // Supply
        if (employeeToShiftMap == null) {
            // TODO employees with no shifts are lacking.
            employeeToShiftMap = shiftList.stream().collect(Collectors.groupingBy(Shift::getEmployee));
        }
        return employeeToShiftMap;
    }

}
