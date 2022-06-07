// SPDX-License-Identifier: GPL-3.0
        
pragma solidity >=0.4.22 <0.9.0;

// This import is automatically injected by Remix
import "remix_tests.sol"; 

// This import is required to use custom transaction context
// Although it may fail compilation in 'Solidity Compiler' plugin
// But it will work fine in 'Solidity Unit Testing' plugin
import "remix_accounts.sol";
import "SixG_Strategy.sol";
// <import file to test>

// File name has to end with '_test.sol', this file can contain more than one testSuite contracts
contract testSuite {

    SixG_Strategy sc = new SixG_Strategy();

    /// 'beforeAll' runs before all other tests
    /// More special functions are: 'beforeEach', 'beforeAll', 'afterEach' & 'afterAll'
    function beforeAll() public {
        // <instantiate contract>
        sc = new SixG_Strategy();
        
    }

    function makeStrategyTest() public {
        // Use 'Assert' methods: https://remix-ide.readthedocs.io/en/latest/assert_library.html
        sc.makeStrategy(0,0,1,1000,2000,SixG_Strategy.ConnectionType.WIFI,SixG_Strategy.Priority.HIGH,"a","a");
        SixG_Strategy.Strategy memory s1 = SixG_Strategy.Strategy(sc.getID()-1,SixG_Strategy.Location(0,0,1),1000,2000,SixG_Strategy.ConnectionType.WIFI,SixG_Strategy.Priority.HIGH,"a","a");
        SixG_Strategy.Strategy memory s2 = sc.getStrategies()[0];
        Assert.equal(s2.id,s1.id, "ID should be the same");
    }

    function deleteStrategyTest() public {
        // Use the return value (true or false) to test the contract
        sc.makeStrategy(0,0,1,1000,2000,SixG_Strategy.ConnectionType.WIFI,SixG_Strategy.Priority.HIGH,"a","a");
        uint len = sc.getStrategies().length;
        sc.deleteStrategy(0);
        Assert.equal(len-1,sc.getStrategies().length, "length should have decreased");
    }
    
    function changePriorityTest() public {
        sc.makeStrategy(0,0,1,1000,2000,SixG_Strategy.ConnectionType.WIFI,SixG_Strategy.Priority.HIGH,"a","a");
        sc.changePriority(0,SixG_Strategy.Priority.LOW);
        SixG_Strategy.Priority p = sc.getStrategies()[0].priority;
        Assert.ok(p==SixG_Strategy.Priority.LOW, "priority should be the new");
    }
}
    