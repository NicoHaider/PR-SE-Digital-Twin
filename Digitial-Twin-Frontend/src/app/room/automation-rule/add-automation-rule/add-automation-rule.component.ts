import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AutomationRuleService} from "../automation-rules.service";

@Component({
  selector: 'app-add-automation-rule',
  templateUrl: './add-automation-rule.component.html',
  styleUrl: './add-automation-rule.component.css'
})
export class AddAutomationRuleComponent {
  ruleForm: FormGroup;
  roomId = this.route.snapshot.params['id'];

  constructor(private route: ActivatedRoute,private router: Router,private automationRuleService: AutomationRuleService) {
    this.ruleForm = new FormGroup({
      'action': new FormControl(null, Validators.required),
      'condition': new FormControl(null, Validators.required),
      'trigger': new FormControl(null, Validators.required)
    });
  }

  onCancel() {
    this.router.navigate(['/rules/', this.roomId]);
  }

  onSubmit() {
    if(this.ruleForm.valid){
      const data = this.ruleForm.value;
      data.roomId = this.roomId;
      this.automationRuleService.saveAutomationRule(data).subscribe(res=>{
        console.log("data", data);
        this.onCancel();
      }, error=>{
        console.log(error)
      });
    }
  }
}
