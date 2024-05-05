import { TestBed, type ComponentFixture } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { RouterTestingModule } from '@angular/router/testing';


describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ 
       
       RouterTestingModule    
      ],
      declarations: [AppComponent],
    });
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should display the correct title', () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain(component.userName);
  });

  it('should display static text "Smart Home"', () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('p').textContent).toContain('Smart Home');
  });

  it('should have a router outlet', () => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('router-outlet')).not.toBeNull();
  });
});