import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutomationRuleService } from './automation-rule.service';
import { AutomationRule } from './automation-rule.model';

@Component({
  selector: 'app-automation-rule',
  templateUrl: './automation-rule.component.html',
  styleUrls: ['./automation-rule.component.css']
})
export class AutomationRuleComponent implements OnInit {
  automationRules: AutomationRule[] = [];
  newRule: AutomationRule = { id: 0, trigger: '', action: '', condition: '' };

  constructor(private automationRuleService: AutomationRuleService, private router: Router) { }


  ngOnInit(): void {
    this.getAutomationRules();
  }

  getAutomationRules(): void {
    this.automationRuleService.getAllAutomationRules()
      .subscribe(
        rules => this.automationRules = rules,
        error => console.error(error)
      );
  }

  saveAutomationRule(): void {
    this.automationRuleService.saveAutomationRule(this.newRule)
      .subscribe(
        rule => {
          this.automationRules.push(rule);
          this.newRule = { id: 0, trigger: '', action: '', condition: '' };
        },
        error => console.error(error)
      );
  }
  addNewRule(): void {
    this.router.navigate(['/add-automation-rule']);
  }

  deleteAutomationRule(id: number): void {
    this.automationRuleService.deleteAutomationRule(id)
      .subscribe(
        () => this.automationRules = this.automationRules.filter(rule => rule.id !== id),
        error => console.error(error)
      );
  }
}
