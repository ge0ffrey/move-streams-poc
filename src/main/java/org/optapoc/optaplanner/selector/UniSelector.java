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

import org.optapoc.domain.Employee;
import org.optapoc.domain.Shift;
import org.optapoc.optaplanner.move.Pillar;

public class UniSelector<A> {

    private MoveStreamFactory moveStreamFactory;
    private Class<A> aClass;

    public UniSelector(MoveStreamFactory moveStreamFactory, Class<A> aClass) {
        this.moveStreamFactory = moveStreamFactory;
        this.aClass = aClass;
    }

    public <B> BiSelector<A, B> join(Class<B> bClass) {
        UniSelector<B> other = moveStreamFactory.select(bClass);
        return new BiSelector<>(moveStreamFactory, this, other);
    }

    public <ValueB_, EntityB_> BiSelector<A, Pillar<ValueB_, EntityB_>>
            joinPillar(Class<EntityB_> entityBClass, Function<EntityB_, ValueB_> entityToValueBFunction) {
        UniSelector<Pillar<ValueB_, EntityB_>> other = moveStreamFactory.selectPillar(entityBClass, entityToValueBFunction);
        return new BiSelector<>(moveStreamFactory, this, other);
    }

    // Internal API
    public A pick() {
        List<A> aList;
        if (aClass.isAssignableFrom(Shift.class)) {
            aList = (List<A>) moveStreamFactory.getShiftList();
        } else if (aClass.isAssignableFrom(Employee.class)) {
            aList = (List<A>) moveStreamFactory.getEmployeeList();
        } else {
            throw new UnsupportedOperationException("Unknown aClass " + aClass);
        }
        return aList.get(moveStreamFactory.getRandom().nextInt(aList.size()));
    }

}
