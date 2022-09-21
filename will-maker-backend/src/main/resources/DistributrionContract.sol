// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;
import "github.com/provable-things/ethereum-api/contracts/solc-v0.8.x/provableAPI.sol";

contract OracleContract is usingProvable {

   string public transactionStatus;
   address payable benAddress;

   function __callback(bytes32 myid, string memory result) public {
       transactionStatus = "[Ex]: Bad execution";

       if (msg.sender != provable_cbAddress()) revert();


       if (keccak256(abi.encodePacked(result)) == keccak256(abi.encodePacked("resting in peace"))) {
           transactionStatus = "[OK]: time to distribute";


            if (benAddress == address(0)) {
                transactionStatus = "Address is not set yet. ";
                return;
            }
            transactionStatus = "[OK]: Begin distribution...";
            (bool sent, bytes memory data) = benAddress.call{value: address(this).balance}("");

            if (sent == false) {
                transactionStatus = "[Ex]: Distribution failed!";
            }
            require(sent, "Failed to send Ether");

            transactionStatus = "[OK]: Distribution initiated!";
       } else if (keccak256(abi.encodePacked(result)) == keccak256(abi.encodePacked("alive and well"))) {
           transactionStatus = "continue mining...";
       } else {
           transactionStatus = "Exception thrown when calling API...";
       }

   }

   function setBenAddress(address payable newBen) public {
       benAddress = newBen;
   }



   function checkAliveStatus() public {
       if (benAddress == address(0)) {
          transactionStatus = "Address is not set yet. ";
            return;
        }


        if (address(this).balance == 0) {
            transactionStatus = "Contract balance is 0. What's the point?";
            return;
        }
       if (provable_getPrice("URL") > address(this).balance) {
       } else {
           transactionStatus = "Start querying data";
           provable_query("URL", "json(https://is-alive.free.beeceptor.com/123456).alive");
       }
   }

    receive() payable external {}
}