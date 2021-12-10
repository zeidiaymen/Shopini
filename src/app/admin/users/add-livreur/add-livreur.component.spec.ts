import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLivreurComponent } from './add-livreur.component';

describe('AddLivreurComponent', () => {
  let component: AddLivreurComponent;
  let fixture: ComponentFixture<AddLivreurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLivreurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLivreurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
