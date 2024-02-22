import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';

import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> | null = null;

  constructor(
      private coursesService: CoursesService,
      public dialog: MatDialog,
      private router: Router,
      private route: ActivatedRoute,
      private snackBar: MatSnackBar,
    ) {

    this.refresh();
  }

  refresh() {
    this.courses$ = this.coursesService.findAll()
      .pipe(
        catchError(error => {
          this.onError('Error while loading courses.');
          // console.log(error);
          return of([]);
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  ngOnInit(): void {
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }

  onRemove(course: Course) {
    this.coursesService.remove(course._id).subscribe({
      next: () => {
        this.snackBar.open('Course removed successfully.', 'X', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });
      },
      error: () => this.onError('Error while removing course.')
    });
    this.refresh();
  }

}
