import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { Lesson } from '../../model/lesson';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent implements OnInit {

  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private coursesService: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course']; // esse 'course' tem que ser o nome exatamente igual ao que está no arquivo 'courses-routing.module.ts', dentro do `resolve` do `routes`!

    this.form = this.formBuilder.group({
      _id: [course._id],
      name: [course.name, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(100)
      ]],
      category: [course.category, [Validators.required]],
      lessons: this.formBuilder.array(this.retrieveLessons(course), Validators.required)
    });
    // console.log(this.form);
    // console.log(this.form.value);


  }

  private retrieveLessons(course: Course) {
    const lessons = [];

    if (course?.lessons) {
      course.lessons
        .forEach((lesson) => lessons.push(this.createLesson(lesson)));
    } else {
      lessons.push(this.createLesson());
    }
    return lessons;
  }

  private createLesson(lesson: Lesson = { id: '', name: '', youtubeUrl: '' }) {
    return this.formBuilder.group({
      id: [lesson.id],
      name: [lesson.name, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(100)
      ]],
      youtubeUrl: [lesson.youtubeUrl, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(11)
      ]]
    })
  }

  getLessonsFormArray() {
    return (<UntypedFormArray>this.form.get('lessons')).controls;
  }

  addNewLesson() {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    lessons.push(this.createLesson());
  }

  removeLesson(index: number) {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    lessons.removeAt(index);
  }

  onSubmit() {
    if (this.form.valid) {
      this.coursesService.save(this.form.value)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError()
      });
    } else {
      alert('Invalid form');
    }


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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'This field is required';
    }

    if (field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `This field must have at least ${requiredLength} characters`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `This field must have a maximum of ${requiredLength} characters`;
    }

    return 'Invalid field';

  }

  isFormArrayRequires() {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    return !lessons.valid && lessons.hasError('required') && lessons.touched;
  }

}
