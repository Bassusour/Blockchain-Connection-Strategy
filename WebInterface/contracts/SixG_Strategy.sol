// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;


contract SixG_Strategy {

    address private owner;

    struct Location {
        int32 x;
        int32 y;
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

    constructor() public {
        owner = msg.sender;
    }

    modifier isOwner() {
        require(msg.sender == owner, "caller is not owner");
        _;
    }
    
    function updateOwner(address newOwner) public isOwner {
        owner = newOwner;
    }
    
    function getOwner() public view returns (address) {
        return owner;
    }
    
    function makeStrategy( int32 x, int32 y, uint32 radius, 
                            uint startDate, uint endDate, 
                            ConnectionType connectionType,
                            Priority priority, string memory description,
                            string memory name) public isOwner {
        Location memory location = Location(x, y , radius);
        Strategy memory strategy = Strategy(location, startDate, endDate, connectionType, priority, description, name);
        strategies.push(strategy);
        length = strategies.length;
        emit strategyChange();
    }

    function deleteStrategy(uint index) public isOwner{
        require(index < strategies.length && index >= 0);
        strategies[index] = strategies[strategies.length - 1];
        strategies.pop();
        emit strategyChange();
    }

    function changePriority(uint index, Priority priority) public isOwner {
        require (index < strategies.length && index >= 0);
        strategies[index].priority = priority;
    }

    function getStrategies() public view returns (Strategy[] memory){
        return strategies;
    }

    function getStrategyFromIndex(uint index) public view returns (Strategy memory){
        require (index < strategies.length && index >= 0);
        return strategies[index];
    } 

    function getLenght() public view returns (uint){
        return length;
    }
}