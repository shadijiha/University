class Shape
    @@idcount = 0
    def initialize
        @@idcount += 1
        @id = @@idcount
    end
    
    def print()
        @peri
        @are
        if self.perimeter == nil
            @peri = "undefined"
        else
            @peri = self.perimeter
        end
        if self.area == nil
            @are = "undefined"
        else
            @are = self.area
        end
        
        puts "#{@id}: #{self.class}, Perimeter: #{@peri}, Area: #{@are}"
    end
    
    def perimeter()
        nil
    end
    
    def area()
        nil
    end
end

class Circle < Shape
    
    def initialize(radius)
        super()
        @radius = radius
    end
    
    def perimeter()
        return 3.14159265358979 * 2 * @radius
    end
    
    def area()
        return 3.14159265358979 * @radius * @radius
    end
    
end

class Ellipse < Shape
   
   def initialize(a, b)
       super()
       
       if a<b
           @major = b
           @minor = a
        else
            @major = a
            @minor = b
        end
   end
  
  def area()
      return 3.14159265358979 * @major * @minor
  end
  
  def eccentricity()
      
      begin
        
        return Math.sqrt((@major*@major) - (@minor*@minor))
      #major or a to be used?
      rescue
        return nil
      
      end
      
  end
    
end

class Rhombus < Shape
    
    def initialize(q, r)
        super()
       @diag1 = q
       @diag2 = r
    end
    
    def perimeter()
       return 2 * Math.sqrt((@diag1*@diag1)+(@diag2*@diag2)) 
    end

    def area()
       return (@diag1 * @diag2)/2 
    end
    
    def inradius()
       
       begin
       
       return (@diag1*@diag2)/(self.perimeter)
       
       rescue
       
        return nil
       
       end
       
    end
    
end


s1 = Shape.new
s1.print

s2 = Circle.new(3)
s2.print

s3 = Ellipse.new(3, 7)
s3.print

s4 = Rhombus.new(5,10)
s4.print
puts s4.inradius

