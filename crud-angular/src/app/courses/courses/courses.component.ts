import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
// import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent implements OnInit {
  courses: Course[] = [
    { _id: '1', name: 'Angular', category: 'Front-end' }
  ];
  displayedColumns = ['name', 'category'];

  constructor() {
    // this.courses = []; // É possível inicializar o array aqui ou na linha acima onde foi declarado.
  }

  ngOnInit(): void {

  }

}
