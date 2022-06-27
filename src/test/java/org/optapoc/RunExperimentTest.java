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

package org.optapoc;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.optapoc.domain.Employee;
import org.optapoc.domain.Shift;
import org.optapoc.optaplanner.move.Moves;
import org.optapoc.optaplanner.move.PillarSwapMove;
import org.optapoc.optaplanner.move.SwapMove;
import org.optapoc.optaplanner.selector.MoveStreamFactory;

class RunExperimentTest {

    Employee ann = new Employee("Ann", "smart");
    Employee beth = new Employee("Beth", "smart");
    Employee carl = new Employee("Carl", "smart");
    Employee dan = new Employee("Dan", "strong");
    Employee edna = new Employee("Edna", "strong");
    List<Employee> employeeList = List.of(
            ann,
            beth,
            carl,
            dan,
            edna
    );
    List<Shift> shiftList = List.of(
            new Shift("mon 06:00-14:00", "smart", ann),
            new Shift("mon 14:00-22:00", "smart", beth),
            new Shift("mon 14:00-22:00", "strong", carl),
            new Shift("mon 22:00-06:00", "smart", dan),
            new Shift("tue 06:00-14:00", "smart", ann),
            new Shift("tue 14:00-22:00", "smart", beth),
            new Shift("tue 14:00-22:00", "strong", carl),
            new Shift("tue 22:00-06:00", "smart", dan)
    );

    @Test
    public void select() {
        MoveStreamFactory moveStreamFactory = new MoveStreamFactory(employeeList, shiftList);

        Iterator<SwapMove<Shift>> moveIterator
                = moveStreamFactory.select(Shift.class)
                    .join(Shift.class)
                    .move(Moves.swap());


        while (moveIterator.hasNext()) {
            SwapMove<Shift> move = moveIterator.next();
            System.out.println("Move " + move);
        }
        // TODO do step and iterate again
    }

    @Test
    public void selectPillar() {
        MoveStreamFactory moveStreamFactory = new MoveStreamFactory(employeeList, shiftList);

        Iterator<PillarSwapMove<Employee, Shift>> moveIterator
                = moveStreamFactory.selectPillar(Shift.class, Shift::getEmployee)
                        .joinPillar(Shift.class, Shift::getEmployee)
                        .move(Moves.swapPillar());

        while (moveIterator.hasNext()) {
            PillarSwapMove<Employee, Shift> move = moveIterator.next();
            System.out.println("Move " + move);
        }
        // TODO do step and iterate again
    }

}
