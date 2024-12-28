import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorAddComponent } from './director-add.component';

describe('DirectorAddComponent', () => {
  let component: DirectorAddComponent;
  let fixture: ComponentFixture<DirectorAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DirectorAddComponent]
    });
    fixture = TestBed.createComponent(DirectorAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
