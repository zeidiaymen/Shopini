import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MrouterComponent } from './mrouter.component';

describe('MrouterComponent', () => {
  let component: MrouterComponent;
  let fixture: ComponentFixture<MrouterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MrouterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MrouterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
