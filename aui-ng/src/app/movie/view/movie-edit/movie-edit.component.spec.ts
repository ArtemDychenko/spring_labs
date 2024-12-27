import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieEditComponent } from './movie-edit.component';

describe('CharacterEditComponent', () => {
  let component: MovieEditComponent;
  let fixture: ComponentFixture<MovieEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieEditComponent]
    });
    fixture = TestBed.createComponent(MovieEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
