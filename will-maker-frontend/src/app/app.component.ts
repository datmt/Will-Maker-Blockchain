import {Component} from '@angular/core';
import {WillService} from "./services/will.service";
import {WillDetails} from "./interfaces/will-details";
import {Beneficiary} from "./interfaces/beneficiary";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'will-maker';
  will: WillDetails = {
    creatorEtherAddress: "",
    creatorSocialId: "",
    etherAmount: 0,
    beneficiaryList: []
  };
  benName: string = "";
  benAddress: string = "";
  benPercentage: number = 0.0;
  displayedColumns: string[] = ['name', 'etherAddress', 'percentage', 'action'];
  dataSource: MatTableDataSource<Beneficiary> = new MatTableDataSource<Beneficiary>();

  constructor(private willService: WillService) {
    willService.getWill().subscribe(data => {
      this.will = data;
      if (!this.will.beneficiaryList) {
        this.will.beneficiaryList = [];
      }

      this.dataSource.data = this.will.beneficiaryList;
    })
  }

  addNewBen() {
    if (!this.will.beneficiaryList) {
      console.log('empty!')
      return;
    }

    const existingBenIndex = this.findIndexByEtherAddress(this.benAddress);


    const ben = {
      name: this.benName,
      etherAddress: this.benAddress,
      percentage: this.benPercentage
    };

    if (existingBenIndex != -1) {
      this.will.beneficiaryList[existingBenIndex] = ben;
    } else {
      this.will.beneficiaryList.push(ben);
    }
    this.dataSource.data = this.will.beneficiaryList;
  }


  editBen(benAddress: string) {
    if (!this.will.beneficiaryList) {
      return;
    }
    const benIndex = this.findIndexByEtherAddress(benAddress);
    if (benIndex === -1) {
      console.warn("Invalid ben");
      return;
    }

    const ben = this.will.beneficiaryList[benIndex];

    this.benPercentage = ben.percentage;
    this.benAddress = ben.etherAddress;
    this.benName = ben.name;
  }

  deleteBen(benAddress: string) {
    if (!this.will.beneficiaryList)
      return;
    console.log("deleting ben:", benAddress, this.findIndexByEtherAddress(benAddress));
    const index = this.findIndexByEtherAddress(benAddress);
    this.will.beneficiaryList?.splice(index, 1);
    this.dataSource.data = this.will.beneficiaryList;
  }

  private findIndexByEtherAddress(benAddress: string) {
    if (!this.will.beneficiaryList) {
      return -1;
    }
    for (let i = 0; i < this.will.beneficiaryList?.length; i++) {
      if (this.will.beneficiaryList[i].etherAddress === benAddress)
        return i;
    }

    return -1;
  }

  saveWill() {
    console.log("saving: ", this.will);
    if (!this.will.creatorEtherAddress || !this.will.creatorSocialId) {
      alert("Your ether address or social id is not valid");
      return;
    }
    this.willService.saveWill(this.will).subscribe(data => {
      console.log(data);
    })
  }

  createContract() {

  }
}
