package com.suslov.jetbrains;

import com.suslov.jetbrains.models.GameGridTest;
import com.suslov.jetbrains.models.GameManagerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Mikhail Suslov
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({GameGridTest.class, GameManagerTest.class})
public class AllTestCase {
}
