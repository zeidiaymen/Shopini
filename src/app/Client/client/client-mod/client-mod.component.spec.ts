import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientModComponent } from './client-mod.component';

describe('ClientModComponent', () => {
  let component: ClientModComponent;
  let fixture: ComponentFixture<ClientModComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientModComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientModComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
