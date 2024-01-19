import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private coursesService: CoursesService,
    private snackBar: MatSnackBar
    ) {
    this.form = this.formBuilder.group({
      name : [null],
      category: [null]
    })
  }

  ngOnInit(): void {

  }

  onSubmit() {
    this.coursesService.save(this.form.value)
      .subscribe({
        next: (data) => console.log(data),
        error: () => this.onError()
      });
  }

  onCancel() {
    console.log('onCancel');
  }

  private onError() {
    this.snackBar.open('Error saving the course.', '', { duration: 3000 });
  }

}
