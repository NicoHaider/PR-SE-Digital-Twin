import {Component, Input} from '@angular/core';
import {Device} from "../../../device/device.model";
import {AutomationRule} from "../automation-rules.model";
import {DeviceService} from "../../../device/device.service";
import {RoomService} from "../../room.service";
import {Router} from "@angular/router";
import {AutomationRuleService} from "../automation-rules.service";

@Component({
  selector: 'app-rule-item',
  templateUrl: './rule-item.component.html',
  styleUrl: './rule-item.component.css'
})
export class RuleItemComponent {
  @Input() roomId: number;
  @Input() automationRule: AutomationRule;
  @Input() index: number = 0;

  constructor(private automationRuleService: AutomationRuleService, private roomService : RoomService, private router: Router) {
  }


  onEdit() {
    this.router.navigate(['/update-rule/'+ this.roomId + '/' + this.automationRule.id]);
  }

  onDelete() {
    console.log(this.automationRule);
    this.automationRuleService.deleteAutomationRule(this.automationRule.id).subscribe(res=>{
      location.reload();
    }, error=>{
      console.log("delete error");
      console.log(error)
    });
  }
}
