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
        
        puts "#{@id}: #{self.class}, perimeter: #{@peri}, area: #{@are}"
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
        if @radius < 0
            return "undefined"
        else
            return 3.14159265358979 * 2 * @radius
        end
    end
    
    def area()
        if @radius < 0
            return "undefined"
        else
            return 3.14159265358979 * @radius * @radius
        end
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
    if (@major < 0 || @minor < 0)
        return "undefined"
    else
      return 3.14159265358979 * @major * @minor
    end
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
        if (@diag1 < 0 || @diag2 < 0)
            return "undefined"
        else
            return 2 * Math.sqrt((@diag1*@diag1)+(@diag2*@diag2)) 
        end
    end

    def area()
        if (@diag1 < 0 || @diag2 < 0)
            return "undefined"
        else
            return (@diag1 * @diag2)/2 
        end
    end
    
    def inradius()
       
       begin
       
       return (@diag1*@diag2)/(self.perimeter)
       
       rescue
       
        return nil
       
       end
       
    end
    
end


stats = {"Circle" => 0,
        "Ellipse"=> 0,
        "Rhombus"=> 0,
        "Shape"=> 0}


File.open("test.txt") do |record|
    record.each do |item|
        shape, param1, param2 = item.split(" ")

        if shape == "shape"
            s = Shape.new
            s.print
            stats["Shape"] += 1
        end

        if shape == "circle"
            if(param1.to_i <0)
                puts "Error: Invalid Circle"

            end

            s = Circle.new(param1.to_i)
            s.print
            stats["Circle"] += 1
            stats["Shape"] += 1
        end

        if shape == "ellipse"

            if (param1.to_i < 0 || param2.to_i < 0)

                puts "Error: Invalid Ellipse"
            end

            s = Ellipse.new(param1.to_i,param2.to_i)
            s.print
            stats["Ellipse"] += 1
            stats["Shape"] += 1

            if(param1.to_i >= 0 && param2.to_i >= 0)
                puts "linear eccentricity: #{s.eccentricity}"
            end
        end

        if shape == "rhombus"
            
            if (param1.to_i < 0 || param2.to_i < 0)
                puts "Error: Invalid Rhombus"
            end

            s = Rhombus.new(param1.to_i,param2.to_i)
            s.print
            stats["Rhombus"] += 1
            stats["Shape"] += 1

            if(param1.to_i >= 0 && param2.to_i >= 0)
                puts "in-radius: #{s.inradius}"
            end
        end
    end
end

puts " "
puts "Statistics:"

stats.each_pair do |key, value|
    if key == "Rhombus"
    puts "  #{key}(es): #{value}"
    else
    puts "  #{key}(s): #{value}"
    end
end