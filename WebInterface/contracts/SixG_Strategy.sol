// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;


contract SixG_Strategy {

    address private owner;
    uint gid = 0; // global id

    struct Location {
        int32 x;
        int32 y;
        uint32 radius;
    }
    
    enum ConnectionType { WIFI, DATA }
    enum Priority { LOW, MEDIUM, HIGH }

    struct Strategy {   
        uint id;
        Location location;
        uint startDate;
        uint endDate;
        ConnectionType connectionType;
        Priority priority;
        bytes32 description;
        bytes32 name;
    }

    Strategy[] public strategies;
    uint public length;
    event strategyChange();

    constructor() {
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

    function getID() public view returns (uint) {
        return gid;
    }
    
    function makeStrategy( int32 x, int32 y, uint32 radius, 
                            uint startDate, uint endDate, 
                            ConnectionType connectionType,
                            Priority priority, bytes32 description,
                            bytes32 name) public isOwner {
        Location memory location = Location(x, y , radius);
        Strategy memory strategy = Strategy(gid, location, startDate, endDate, connectionType, priority, description, name);
        strategies.push(strategy);
        gid++;
        emit strategyChange();
    }

    function deleteStrategy(uint id) public isOwner{
        require(id >= 0);
        uint index;
        for(uint i = 0; i < strategies.length; i++) {
            if (strategies[i].id == id){
                index = i;
            }
        }
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


}