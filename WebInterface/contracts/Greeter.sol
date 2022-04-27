//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.0;

import "hardhat/console.sol";

contract Greeter {
    string private greeting;
    string private lat;
    string private lng;
    string private radius;

    constructor(string memory _greeting) {
        console.log("Deploying a Greeter with greeting:", _greeting);
        greeting = _greeting;
    }

    function greet() public view returns (string memory) {
        return greeting;
    }

    function getCircle() public view returns (string memory) {
        return string(abi.encodePacked(lat, ", " , lng , ", ", radius));
    }

    function setGreeting(string memory _greeting) public {
        console.log("Changing greeting from '%s' to '%s'", greeting, _greeting);
        greeting = _greeting;
    }

    function setCircle(string memory _lat, string memory _lng, string memory _radius ) public {
        lat = _lat;
        lng = _lng;
        radius = _radius;
    }
}
