import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {RoomService} from "../../room.service";
import {DeviceService} from "../../../device/device.service";
import {AutomationRuleService} from "../automation-rules.service";

@Component({
  selector: 'app-edit-automation-rule',
  templateUrl: './edit-automation-rule.component.html',
  styleUrl: './edit-automation-rule.component.css'
})
export class EditAutomationRuleComponent implements OnInit{
  ruleForm: FormGroup;
  roomIndex: number = this.route.snapshot.params['roomId'];
  ruleId: number = this.route.snapshot.params['id'];

  constructor(private route: ActivatedRoute,private router: Router, private roomService: RoomService,private automationRuleService: AutomationRuleService) {

  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    let action = '';
    let condition = '';
    let trigger = '';
    this.automationRuleService.getAutomationRuleById(this.ruleId).subscribe(res=>{
      console.log("rule with id " + this.ruleId, res);
      action = res.action;
      condition = res.condition;
      trigger = res.trigger;
      console.log("action", action);
      console.log("condition", condition);
      console.log("trigger", trigger);
    });
    this.ruleForm = new FormGroup({
      'action': new FormControl(action, Validators.required),
      'condition': new FormControl(condition, Validators.required),
      'trigger': new FormControl(trigger, Validators.required)
    });
  }

  onSubmit() {
    const data = this.ruleForm.value;
    data.roomId = this.roomIndex;
    data.id = this.ruleId;
    console.log("data", data);
    this.automationRuleService.updateAutomationRule(data).subscribe(res=>{
      this.router.navigate(['/rules/', this.roomIndex]);
    }, error=>{
      console.log(error)
    });
  }

  onCancel() {
    this.router.navigate(['/rules/', this.roomIndex]);
  }
}
