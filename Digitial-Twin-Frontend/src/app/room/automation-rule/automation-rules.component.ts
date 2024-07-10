import {Component, OnInit} from '@angular/core';
import {AutomationRule} from "./automation-rules.model";
import {AutomationRuleService} from "./automation-rules.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Room, RoomType} from '../room.model';
import {RoomService} from "../room.service";
import {RoomData} from "../roomData.model";

@Component({
  selector: 'app-automation-rules',
  templateUrl: './automation-rules.component.html',
  styleUrl: './automation-rules.component.css'
})
export class AutomationRulesComponent implements OnInit {
  automationRules: AutomationRule[] = [];
  room: Room;
  newRule: AutomationRule = { id: 0, trigger: '', action: '', condition: '' };
  index: number;
  roomId: number;

  constructor(private automationRuleService: AutomationRuleService, private roomService: RoomService, private route: ActivatedRoute, private router: Router) { }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.roomId = params['id'];
    });
    this.roomService.getRoomById(this.roomId).subscribe(res=>{
      this.room = res;
    });
    console.log(this.room);
    this.fetchData();
  }

  fetchData() {
    this.roomService.fetchDataFromBackend(this.room).subscribe((data: RoomData) => {
      console.log(data);
      this.room.co2 = data.co2Level;
      this.room.temperature = data.temperature;
      this.room.people = data.numOfPeople;
      this.room.dateTime = data.dateTime;
    });
  }

  deleteAutomationRule(id: number): void {
    this.automationRuleService.deleteAutomationRule(id)
      .subscribe(
        () => this.automationRules = this.automationRules.filter(rule => rule.id !== id),
        error => console.error(error)
      );
  }

  onBack() {
    this.router.navigateByUrl('/'+this.roomId);
  }

  onAdd() {
    this.router.navigateByUrl('/add-rule/'+ this.room.id);
  }
}
