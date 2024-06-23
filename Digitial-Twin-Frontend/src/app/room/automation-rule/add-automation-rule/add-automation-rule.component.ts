import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AutomationRuleService } from '../automation-rule.service';
import { AutomationRule } from '../automation-rule.model';

@Component({
  selector: 'app-add-automation-rule',
  templateUrl: './add-automation-rule.component.html',
  styleUrls: ['./add-automation-rule.component.css']
})
export class AddAutomationRuleComponent {
  newRule: AutomationRule = { id: 0, trigger: '', action: '', condition: '' };

  constructor(private automationRuleService: AutomationRuleService, private router: Router) { }

  saveRule(): void {
    this.automationRuleService.saveAutomationRule(this.newRule)
      .subscribe(
        () => this.router.navigate(['/automation-rules']),
        error => console.error(error)
      );
  }
}
