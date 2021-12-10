import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoringDeliveryComponent } from './monitoring-delivery.component';

describe('MonitoringDeliveryComponent', () => {
  let component: MonitoringDeliveryComponent;
  let fixture: ComponentFixture<MonitoringDeliveryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonitoringDeliveryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitoringDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
