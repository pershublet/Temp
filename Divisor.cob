       identification division.
       program-id. Divisors.
      
       data division.
      
       linkage section.
       01  num         pic 9(6).
       01  result.
           03  resLen  pic 9(3).
           03  res     pic 9(6) occurs 0 to 200 times
                                depending on resLen
                                indexed by i, j.
       77 N            pic 99.
       77 d            pic 99.
       procedure division using num result.
      
          initialize result
      
      *   set `resLen` to 0 if `num` is a prime number.
          set i to 1.
          set j to 200.
          compute N rounded = function sqrt(num)
      
          if function MOD(num, 2) equals to 0 then
              move 2 to res(i)
              set i up by 1
              set j down by 1
              divide num by 2 giving res(j)
          end-if.
      
          perform varying d from 3 by 1 until d is greater than N
              if function MOD(num, d) equals to 0 then
                  move d to res(i)
                  set i up by 1
                  set j down by 1
                  divide num by d giving res(j)
              end-if
          end-perform.
          
          if res(i) equals to res(j) then
              set j up by 1
          end-if.
      
          perform until j equals to 200
              move res(j) to res(i)
              set i up by 1
              set j up by 2
          end-perform.
      
          goback.
       end program Divisors.
      
