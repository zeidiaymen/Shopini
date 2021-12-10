import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModproductComponent } from './modproduct.component';

describe('ModproductComponent', () => {
  let component: ModproductComponent;
  let fixture: ComponentFixture<ModproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModproductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
