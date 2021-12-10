import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginForTestComponent } from './login-for-test.component';

describe('LoginForTestComponent', () => {
  let component: LoginForTestComponent;
  let fixture: ComponentFixture<LoginForTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginForTestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginForTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
