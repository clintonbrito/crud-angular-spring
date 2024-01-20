import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent implements OnInit {

  // form = this.formBuilder.group({
  //   name: ['', ], // mesma coisa que: new FormControl<string>('')
  //   category: [''] // mesma coisa que: new FormControl<string>('')
  // });

  form = this.formBuilder.group({
    name: [''],
    category: ['']
  })

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private coursesService: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location
    ) {
    // this.form
  }

  ngOnInit(): void {
    // this.form.value.name = 'Bill';
  }

  onSubmit() {
    this.coursesService.save(this.form.value)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError()
      });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Course saved successfully.', '', { duration: 3000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error saving the course.', '', { duration: 3000 });
  }

}
