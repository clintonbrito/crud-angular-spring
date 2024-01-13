import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

// import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent implements OnInit {
  courses: Observable<Course[]>;
  displayedColumns = ['name', 'category'];

  // coursesService: CoursesService;

  constructor(private coursesService: CoursesService) {
    // this.courses = [];
    // this.coursesService = new CoursesService();
    this.courses = this.coursesService.findAll();
  }

  ngOnInit(): void {

  }

}
