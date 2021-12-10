import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeLivreurComponent } from './change-livreur.component';

describe('ChangeLivreurComponent', () => {
  let component: ChangeLivreurComponent;
  let fixture: ComponentFixture<ChangeLivreurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeLivreurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeLivreurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
