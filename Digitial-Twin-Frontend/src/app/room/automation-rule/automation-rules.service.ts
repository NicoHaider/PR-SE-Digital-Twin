import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AutomationRule } from './automation-rules.model';

@Injectable({
  providedIn: 'root'
})
export class AutomationRuleService {
  apiUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  updateAutomationRule(rule: AutomationRule): Observable<AutomationRule> {
    return this.http.put<AutomationRule>(this.apiUrl + "api/rules", rule)
      .pipe(
        catchError(this.handleError)
      );
  }

  getAutomationRuleById(id: number): Observable<AutomationRule> {
    const url = `${this.apiUrl}api/rules/${id}`;
    return this.http.get<AutomationRule>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  saveAutomationRule(rule: AutomationRule): Observable<AutomationRule> {
    return this.http.post<AutomationRule>(this.apiUrl + "api/rules", rule)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteAutomationRule(id: number): Observable<void> {
    const url = `${this.apiUrl}api/rules/${id}`;
    return this.http.delete<void>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any) {
    console.error('An error occurred', error);
    return throwError('Something went wrong; please try again later.');
  }
}
