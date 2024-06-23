import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutomationRuleService } from '../automation-rule.service';
import { Room } from '../../room.model';
import { RoomService } from '../../room.service';

@Component({
  selector: 'app-automation-rules-overview',
  templateUrl: './automation-rules-overview.component.html',
  styleUrls: ['./automation-rules-overview.component.css']
})
export class AutomationRulesOverviewComponent implements OnInit {
  rooms: Room[] = [];

  constructor(
    private automationRuleService: AutomationRuleService,
    private roomService: RoomService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.roomService.getRooms().subscribe((rooms: Room[]) => {
      this.rooms = rooms;
    });
  }

  navigateToAddRule(): void {
    this.router.navigate(['/add-automation-rule']);
  }
}
