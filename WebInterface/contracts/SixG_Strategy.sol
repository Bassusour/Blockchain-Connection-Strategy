// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;


contract SixG_Strategy {

    struct Location {
        uint32 x;
        uint32 y;
        uint32 radius;
    }
    
    enum ConnectionType { WIFI, DATA }
    enum Priority { LOW, MEDIUM, HIGH }

    struct Strategy {   
        Location location;
        uint startDate;
        uint endDate;
        ConnectionType connectionType;
        Priority priority;
        string description;
        string name;
    }

    Strategy[] public strategies;
    uint public length;
    event strategyChange();
    
    function makeStrategy( uint32 x, uint32 y, uint32 radius, 
                            uint startDate, uint endDate, 
                            ConnectionType connectionType,
                            Priority priority, string memory description,
                            string memory name) public {
        Location memory location = Location(x, y , radius);
        Strategy memory strategy = Strategy(location, startDate, endDate, connectionType, priority, description, name);
        strategies.push(strategy);
        length = strategies.length;
        emit strategyChange();
    }

    function deleteStrategy(uint index) public{
        require(index < strategies.length && index >= 0);
        strategies[index] = strategies[strategies.length - 1];
        strategies.pop();
        emit strategyChange();
    }

    function changePriority(uint index, Priority priority) public {
        require (index < strategies.length && index >= 0);
        strategies[index].priority = priority;
    }

    function getStrategies() public view returns (Strategy[] memory){
        return strategies;
    }

    function getStrategyFromIndex(uint index) public view returns (Strategy memory){
        return strategies[index];
    } 

    function getLenght() public view returns (uint){
        return length;
    }
}